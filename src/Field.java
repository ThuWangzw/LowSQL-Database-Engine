import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

public class Field {

    //fields in a record
    private Object value;
    private int size;
    TableAttribute attribute;

    //read the field from the file
    public Field(TableAttribute attr,byte[] data){
        size = -1;
        attribute = attr;
        int the_type = attribute.getType();
        if (the_type < Util.VARCHAR){
            size = Util.DataTypeSize[the_type];
        }
        value = toValue(data);
    }


    //add a new field
    public Field(Object v,TableAttribute attr){
        value = v;
        attribute = attr;
        int type = attribute.getType();
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
        int type = attribute.getType();
        try {
            if (type == Util.CHAR) {
                out.writeChar((char) value);
            } else if (type == Util.INT) {
                out.writeInt((int) value);
            } else if (type == Util.FLOAT) {
                out.writeFloat((float) value);
            } else if (type == Util.DOUBLE) {
                out.writeDouble((double) value);
            } else if (type == Util.LONG){
                out.writeLong((long) value);
            } else{
                out.writeBytes((String) value + "\0");
            }
            out.flush();
            return byt.toByteArray();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object toValue(byte[] data){
        int type = attribute.getType();
        if (type == Util.CHAR) {
            return (char) data[0];
        } else if (type == Util.INT) {
            return Util.byte2int(data);
        } else if (type == Util.FLOAT){
            return Util.byte2float(data);
        } else if (type == Util.DOUBLE){
            return Util.byte2Double(data);
        } else if (type == Util.LONG){
            return Util.byte2Long(data);
        }else {
            size = data.length;
            return new String(data);
        }
    }

    public Object getValue(){
        return value;
    }
}
