package Network;

/**
 * Created by Nam Tran on 17-Dec-15.
 */
public class NetworkUtil {

    /**
     * return string Ip from int IPV4
     * @param ipv4
     * @return String IP
     */
    private String formatIp(int ipv4) {
        String int2hex = Integer.toHexString(ipv4);
        StringBuffer ipv4buffer = new StringBuffer();
        String[] ipv4str = new String[4];
        int len = 8;
        for(int i = 0; i < ipv4str.length; i++){
            ipv4str[i] = int2hex.substring(len - 2 * (i + 1), len - 2 * i);
            ipv4buffer.append(Integer.valueOf(ipv4str[i], 16) + ".");
        }
        ipv4buffer.deleteCharAt(ipv4buffer.length()-1);

        return ipv4buffer.toString();
    }
}
