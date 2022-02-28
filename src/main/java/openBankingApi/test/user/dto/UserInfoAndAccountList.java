package openBankingApi.test.user.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserInfoAndAccountList {
	String api_tran_id;
	String api_tran_dtm;
	String rsp_code;
	String rsp_message;
	String user_seq_no;
	String user_ci;
	String user_name;
	String user_info;
	String user_gender;
	String user_cell_no;
	String user_email;
	String res_cnt;
	List<EmdAccountInfo> res_list;
}
