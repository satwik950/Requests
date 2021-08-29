import java.net.*;
import java.io.*;
import java.util.concurrent.ThreadLocalRandom;
import java.lang.*;

class imp {
    public static void main(String a[]) throws Exception {
        Socket s = new Socket(InetAddress.getLocalHost(), 10);
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintStream p = new PrintStream(s.getOutputStream());
        int i = 0, rptr = -1, nf, rws = 8;
        String rbuf[] = new String[8];
        String ch;

        String temp = "";
        System.out.println();
        do {
            nf = Integer.parseInt(in.readLine());
            if (nf <= rws - 1) {
                for (i = 1; i <= nf; i++) {
                    rptr = ++rptr % 8;
                    rbuf[rptr] = in.readLine();
                    temp = temp + rbuf[rptr] + " ";
                }

                int randomNum = ThreadLocalRandom.current().nextInt(0, temp.length());

                int len1 = temp.substring(0, randomNum).replaceAll("\\s", "").length();
                int len2 = temp.substring(randomNum, temp.length()).replaceAll("\\s", "").length();
                int out = len1 + len2;
                System.out.println("Total bytes received are " + out);
                System.out.println("Received bytes frame 1 are " + temp.substring(0, randomNum)
                        + " Bytes of characters :" + len1 + "\nReceived bytes frame 2 are "
                        + temp.substring(randomNum, temp.length()) + "Bytes of characters : " + len2);
                for (int j = 0; j <= rptr; j++) {
                    System.out.println("The Original message Bytes for message " + j + " are : " + rbuf[j]);
                }
                rws -= nf;
                System.out.println("\nAcknowledgment sent\n");
                p.println(rptr + 1);
                rws += nf;
            } else
                break;
            ch = in.readLine();
        } while (ch.equals("yes"));
    }
}