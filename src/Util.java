import java.lang.instrument.Instrumentation;

public class Util{
    public static String DataStorageDir = "data/";
    public static String MetaDataFile = "data/metadata.bin";
    public static int DiskBlockSize = 4096;

    public static int DatabaseNameMaxLength = 28;  //byte
    public static int TableNameMaxLength = 28;
    public static int AttributeNameMaxLength = 28;

    public static byte[] int2byte(int v){
        // convert int to byte array
        return new byte[]{
            (byte) ((v >> 24) & 0xff), (byte) ((v >> 16) & 0xff),(byte) ((v >> 8) & 0xff),(byte) (v & 0xff)
        };
    }

    public static int byte2int(byte[] v){
        return  v[3] & 0xff | (v[2] & 0xff) << 8 | (v[1] & 0xff) << 16 | (v[0] & 0xff) << 24;
    }

    public static float byte2float(byte[] v) {
        int l = v[3] & 0xff | (v[2] & 0xff) << 8 | (v[1] & 0xff) << 16 | (v[0] & 0xff) << 24;
        return Float.intBitsToFloat(l);
    }

    public static double byte2Double(byte[] v){
        long l = 0;
        for (int i = 0; i < 8; i++) {
            l |= ((long) (v[i] & 0xff)) << (8 * i);
        }
        return Double.longBitsToDouble(l);
    }

    public static long byte2Long(byte[] v){
        long l = 0;
        for (int i = 0; i < 8; i++) {
            l |= ((long) (v[i] & 0xff)) << (8 * i);
        }
        return l;
    }

    //Data Type
    public static int CHAR = 0;
    public static int INT = 1;
    public static int FLOAT = 2;
    public static int DOUBLE = 3;
    public static int LONG = 4;
    public static int VARCHAR = 5;
    public static int STRING = 6;
    public static int[] DataTypeSize = new int[]{1,4,4,8,8};
}

