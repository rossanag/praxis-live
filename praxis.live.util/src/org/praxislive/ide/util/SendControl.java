/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright 2011 Neil C Smith.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 3 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 3 for more details.
 *
 * You should have received a copy of the GNU General Public License version 3
 * along with this work; if not, see http://www.gnu.org/licenses/
 *
 *
 * Please visit http://neilcsmith.net if you need additional information or
 * have any questions.
 */
package org.praxislive.ide.util;

import java.util.HashMap;
import java.util.Map;
import org.praxislive.core.Call;
import org.praxislive.core.CallArguments;
import org.praxislive.core.ControlAddress;
import org.praxislive.core.PacketRouter;
import org.praxislive.core.ControlInfo;
import org.praxislive.core.ExecutionContext;
import org.praxislive.core.services.Service;
import org.praxislive.core.services.ServiceUnavailableException;
import org.praxislive.impl.AbstractControl;
import org.praxislive.ide.core.api.Callback;
import org.praxislive.ide.core.api.HubUnavailableException;

/**
 *
 * @author Neil C Smith (http://neilcsmith.net)
 */
public class SendControl extends AbstractControl {

    private Map<Integer, Callback> pending;
    private PacketRouter router;
    private ExecutionContext context;

    public SendControl() {
        pending = new HashMap<Integer, Callback>();
    }

    public void send(ControlAddress to, CallArguments args, Callback callback)
            throws HubUnavailableException {
        ControlAddress from = getAddress();
        boolean quiet = callback == null;
        Call call;
        if (quiet) {
            call = Call.createQuietCall(to, from, context.getTime(), args);
        } else {
            call = Call.createCall(to, from, context.getTime(), args);
        }
        router.route(call);
        if (!quiet) {
            pending.put(call.getID(), callback);
        }
    }

    public void send(Class<? extends Service> service, String control,
            CallArguments args, Callback callback)
            throws HubUnavailableException, ServiceUnavailableException {
        ControlAddress to = ControlAddress.create(findService(service), control);
        send(to, args, callback);
    }

    @Override
    public void hierarchyChanged() {
        super.hierarchyChanged();
        router = getLookup().find(PacketRouter.class).orElse(null);
        context = getLookup().find(ExecutionContext.class).orElse(null);
        for (Callback callback : pending.values()) {
            callback.onError(CallArguments.EMPTY);
        }
        pending.clear();
    }

    @Override
    public void call(Call call, PacketRouter router) throws Exception {
        switch (call.getType()) {
            case RETURN:
                handleResponse(call, false);
                break;
            case ERROR:
                handleResponse(call, true);
                break;
            default:
                throw new UnsupportedOperationException();
        }
    }

    private void handleResponse(Call call, boolean error) {
        Callback callback = pending.remove(call.getMatchID());
        if (callback != null) {
            if (error) {
                callback.onError(call.getArgs());
            } else {
                callback.onReturn(call.getArgs());
            }
        }
    }

    @Override
    public ControlInfo getInfo() {
        return null;
    }
}
