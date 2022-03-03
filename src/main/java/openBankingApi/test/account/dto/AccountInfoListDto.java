package openBankingApi.test.account.dto;

import lombok.Data;

import java.util.List;

@Data
public class AccountInfoListDto {
	String api_tran_id;    				//2ffd133a-d17a-431d-a6a5
	String api_tran_dtm;   				//20190910101921567
	String rsp_code;       				//A0000
	String rsp_message;    				//""
	String user_name;      				//홍길동
	String res_cnt;        				//5
	List<AccountInfoDto> res_list;
}
