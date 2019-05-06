import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class Field {

    //fields in a record
    private Object value;
    private int size;
    private int type;

    //read the field from the file
    public Field(int tp,byte[] data){
        type = tp;
        size = -1;
        if (type < Util.VARCHAR){
            size = Util.DataTypeSize[type];
        }
        toValue(type,data);
    }


    //add a new field
    public Field(Object v,int tp){
        value = v;
        type = tp;
        if (type < Util.VARCHAR){
            size = Util.DataTypeSize[type];
        }
        else{
            //varchar and string are stored in type String
            size = ((String) value).length() + 1;
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

    public Object toValue(int type,byte[] data){
        if (type == Util.CHAR) {
            return (char) data[0];
        } else if (type == Util.INT) {
            return Util.byte2int(data);
        } else if (type == Util.FLOAT){
            return Util.byte2float(data);
        } else if (type == Util.DOUBLE){
            return Util.byte2Double(data);
        } else {
            size = data.length;
            return new String(data);
        }
    }

    public Object getValue(){
        return value;
    }
}
