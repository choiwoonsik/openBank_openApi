package openBankingApi.test.transfer.dto;

import lombok.Data;

@Data
public class TransferWdFinNumRes {
	String api_tran_id; 					//:"2ffd133a-d17a-431d-a6a5",
	String api_tran_dtm; 					//:"20190910101921567",
	String rsp_code; 						//:"A0000",
	String rsp_message; 					//:"",
	String dps_bank_code_std; 				//:"097",
	String dps_bank_code_sub; 				//:"1230001",
	String dps_bank_name; 					//:"오픈은행",
	String dps_account_num_masked; 			//:"000-1230000-***",
	String dps_print_content; 				//:"입금계좌인자내역",
	String dps_account_holder_name; 		//:"허균",
	String bank_tran_id; 					//:"F123456789U4BC34239Z",
	String bank_tran_date; 					//:"20190910",
	String bank_code_tran; 					//:"097",
	String bank_rsp_code; 					//:"000",
	String bank_rsp_message; 				//:"",
	String fintech_use_num; 				//:"123456789012345678901234",
	String account_alias; 					//:"급여계좌",
	String bank_code_std; 					//:"097",
	String bank_code_sub; 					//:"1230001",
	String bank_name; 						//:"오픈은행",
	String account_num_masked; 				//:"000-1230000-***",
	String print_content; 					//:"출금계좌인자내역",
	String account_holder_name; 			//:"홍길동",
	String tran_amt; 						//:"10000",
	String wd_limit_remain_amt; 			//:"9990000"
}
