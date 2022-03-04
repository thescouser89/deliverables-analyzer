/*
 * Copyright (C) 2019 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.pnc.deliverablesanalyzer.utils;

import java.util.HashMap;
import java.util.Map;

import org.jboss.pnc.api.constants.MDCHeaderKeys;
import org.slf4j.MDC;

/**
 * @author <a href="mailto:matejonnet@gmail.com">Matej Lazar</a>
 */
public final class MdcUtils {

    /**
     * Utility classes shouldn't have a public default constructor
     */
    private MdcUtils() {
    }

    /**
     * TODO: what to do with exceptions
     *
     * @param result
     * @param mdcMap
     * @param mdcHeaderKeys
     */
    public static void putMdcToResultMap(
            Map<String, String> result,
            Map<String, String> mdcMap,
            MDCHeaderKeys mdcHeaderKeys) {
        if (mdcMap == null) {
            throw new RuntimeException("Missing MDC map.");
        }
        if (mdcMap.get(mdcHeaderKeys.getMdcKey()) != null) {
            result.put(mdcHeaderKeys.getHeaderName(), mdcMap.get(mdcHeaderKeys.getMdcKey()));
        } else {
            throw new RuntimeException("Missing MDC value " + mdcHeaderKeys.getMdcKey());
        }
    }

    public static Map<String, String> mdcToMapWithHeaderKeys() {
        Map<String, String> result = new HashMap<>();
        Map<String, String> mdcMap = MDC.getCopyOfContextMap();
        putMdcToResultMap(result, mdcMap, MDCHeaderKeys.PROCESS_CONTEXT);
        putMdcToResultMap(result, mdcMap, MDCHeaderKeys.TMP);
        putMdcToResultMap(result, mdcMap, MDCHeaderKeys.EXP);
        putMdcToResultMap(result, mdcMap, MDCHeaderKeys.USER_ID);
        return result;
    }
}
