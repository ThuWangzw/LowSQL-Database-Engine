public class DataUpdateInfo {
    //the class is used to give information to INDEX engine to update index when data changes
    public enum Type{
        INSERT,DELETE,UPDATE
    }
    private int old_page_id;
    private int old_record_id;
    private int new_page_id;
    private int new_record_id;
    private Object search_key;
    private Type type;

    public DataUpdateInfo(Type tp,int o_p_id, int o_r_id, int n_p_id, int n_r_id,Record rd,int search_key_field_id){
        //search_key_field_id is the id of the fields of the record that is set to be the search key
        type = tp;
        old_page_id = o_p_id;
        old_record_id = o_r_id;
        new_page_id = n_p_id;
        new_record_id = n_r_id;
        search_key = rd.getValue()[search_key_field_id];
    }

}
