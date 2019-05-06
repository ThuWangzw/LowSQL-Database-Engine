import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class Field {

    //fields in a record
    private Object value;
    private int size;
    private int type;

    public Field(Object v,int tp){
        value = v;
        type = tp;
        if (type < Util.VARCHAR){
            size = Util.DataTypeSize[type];
        }
        else{
            size = ((String) value).length();
        }
    }

    public int getSize() {return size;}

    public byte[] toBytes() {
        ByteArrayOutputStream byt = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(byt);
        try {
            if (type == Util.CHAR) {
                out.writeChar((char) value);
            } else if (type == Util.INT) {
                out.writeInt((int) value);
            } else if (type == Util.FLOAT) {
                out.writeFloat((float) value);
            } else if (type == Util.DOUBLE) {
                out.writeDouble((double) value);
            } else {
                out.writeBytes((String) value + "\0");
            }
            out.flush();
            return byt.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
