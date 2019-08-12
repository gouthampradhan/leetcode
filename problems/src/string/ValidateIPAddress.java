package string;

/**
 * Created by gouthamvidyapradhan on 01/08/2019 Write a function to check whether an input string is
 * a valid IPv4 address or IPv6 address or neither.
 *
 * <p>IPv4 addresses are canonically represented in dot-decimal notation, which consists of four
 * decimal numbers, each ranging from 0 to 255, separated by dots ("."), e.g.,172.16.254.1;
 *
 * <p>Besides, leading zeros in the IPv4 is invalid. For example, the address 172.16.254.01 is
 * invalid.
 *
 * <p>IPv6 addresses are represented as eight groups of four hexadecimal digits, each group
 * representing 16 bits. The groups are separated by colons (":"). For example, the address
 * 2001:0db8:85a3:0000:0000:8a2e:0370:7334 is a valid one. Also, we could omit some leading zeros
 * among four hexadecimal digits and some low-case characters in the address to upper-case ones, so
 * 2001:db8:85a3:0:0:8A2E:0370:7334 is also a valid IPv6 address(Omit leading zeros and using upper
 * cases).
 *
 * <p>However, we don't replace a consecutive group of zero value with a single empty group using
 * two consecutive colons (::) to pursue simplicity. For example, 2001:0db8:85a3::8A2E:0370:7334 is
 * an invalid IPv6 address.
 *
 * <p>Besides, extra leading zeros in the IPv6 is also invalid. For example, the address
 * 02001:0db8:85a3:0000:0000:8a2e:0370:7334 is invalid.
 *
 * <p>Note: You may assume there is no extra space or special characters in the input string.
 *
 * <p>Example 1: Input: "172.16.254.1"
 *
 * <p>Output: "IPv4"
 *
 * <p>Explanation: This is a valid IPv4 address, return "IPv4". Example 2: Input:
 * "2001:0db8:85a3:0:0:8A2E:0370:7334"
 *
 * <p>Output: "IPv6"
 *
 * <p>Explanation: This is a valid IPv6 address, return "IPv6". Example 3: Input: "256.256.256.256"
 *
 * <p>Output: "Neither"
 *
 * <p>Explanation: This is neither a IPv4 address nor a IPv6 address.
 *
 * <p>Solution: O(N) split the string by each '.' or ':' and then validate each parts.
 */
public class ValidateIPAddress {
  public static void main(String[] args) {

    System.out.println(
        new ValidateIPAddress().validIPAddress("02001:0db8:85a3:0000:0000:8a2e:0370:7334"));
  }

  public String validIPAddress(String IP) {
    if (IP.contains(".")) {
      if (IP.endsWith(".") || IP.startsWith(".")) return "Neither";
      String[] ipv4 = IP.split("\\.");
      if (ipv4.length != 4) return "Neither";
      else {
        for (String part : ipv4) {
          if (part.isEmpty()) return "Neither";
          if (part.length() > 1 && part.startsWith("0")) return "Neither";
          else {
            if (part.length() > 3) return "Neither";
            for (char c : part.toCharArray()) {
              if (c < '0' || c > '9') return "Neither";
            }
            int value = Integer.parseInt(part);
            if (value < 0 || value > 255) return "Neither";
          }
        }
      }
      return "IPv4";
    } else if (IP.contains(":")) {
      if (IP.endsWith(":") || IP.startsWith(":")) return "Neither";
      String[] ipv6 = IP.split(":");
      if (ipv6.length != 8) return "Neither";
      else {
        for (String part : ipv6) {
          if (part.isEmpty()) return "Neither";
          if (part.length() > 4) return "Neither";
          else {
            for (char c : part.toCharArray()) {
              if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F')) {

              } else {
                return "Neither";
              }
            }
          }
        }
      }
      return "IPv6";
    } else return "Neither";
  }
}
