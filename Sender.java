import java.net.*;
import java.io.*;
import java.rmi.*;

public class impnew {
    public static void main(String a[]) throws Exception {
        ServerSocket ser = new ServerSocket(10);
        Socket s = ser.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in1 = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String sbuff[] = new String[8];
        PrintStream p;
        int sptr = 0, sws = 8, nf, ano, i;
        String ch;
        do {
            p = new PrintStream(s.getOutputStream());
            System.out.print("Enter the no. of Byte streams to be transferred : ");
            nf = Integer.parseInt(in.readLine());
            if (nf == 0) {
                System.exit(0);
            }
            p.println(nf);
            if (nf <= sws - 1) {
                System.out.println("Enter " + nf + " Messages to be send\n");
                int len = 0;
                for (i = 1; i <= nf; i++) {
                    sbuff[sptr] = in.readLine();
                    len = len + sbuff[sptr].length();
                    p.println(sbuff[sptr]);
                    sptr = ++sptr % 8;
                }

                System.out.println("Bytes of messages sent is " + len);

                sws -= nf;
                System.out.print("Acknowledgment received");
                ano = Integer.parseInt(in1.readLine());
                System.out.println(" for " + ano + " byte frames");
                sws += nf;
            }

            else {
                System.out.println("The no. of frames exceeds window size");
                break;
            }

            System.out.print("\nDo you wants to send some more bytes : ");
            ch = in.readLine();
            p.println(ch);
        } while (ch.equals("yes"));
        s.close();
    }
}