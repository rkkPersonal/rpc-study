package org.kk.rpc.utils;

import java.net.URI;

/**
 * @author Steven
 * @date 2023年01月22日 10:48
 */
public class URIUtils {


    public static String getParam(URI exportUri, String serializer) {
        String query = exportUri.getQuery();
        String[] split = query.split("&");
        for (String url : split) {
            if (url.startsWith(serializer + "=")) {
                url = url.replace(serializer + "=", "");
                return url;
            }
        }
        return null;
    }
}
