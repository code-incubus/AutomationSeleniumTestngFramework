package utils;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.IOException;
import java.io.InputStream;

public class SSH extends LoggerUtils {

    public static String runCommandAsSuperUser(String remoteDevice, String commandToExecute) throws Exception {
        JSch jsch = new JSch();
        Session session = null;
        StringBuilder output = new StringBuilder();
        int exitStatus = -1;
        try {
            jsch.addIdentity(pathToKey); //TODO Change once you try it on a certain device.
            session = jsch.getSession("root", remoteDevice, 22);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();

            String command = commandToExecute;
            ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
            channelExec.setCommand(command);
            channelExec.setInputStream(null);
            channelExec.setErrStream(System.err);
            InputStream in = channelExec.getInputStream();
            channelExec.connect();

            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) {
                        break;
                    }
                    String outputLine = new String(tmp, 0, i);
                    output.append(outputLine);
                }
                if (channelExec.isClosed()) {
                    if (in.available() > 0) {
                        continue;
                    }
                    exitStatus = channelExec.getExitStatus();
                    System.out.println("exit-status: " + channelExec.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
            channelExec.disconnect();
            session.disconnect();
        } catch (JSchException | IOException e) {
            e.printStackTrace();
        }
        if (exitStatus != 0) {
            throw new Exception("Remote command execution failed with exit status " + exitStatus);
        }
        return output.toString();
    }
}

