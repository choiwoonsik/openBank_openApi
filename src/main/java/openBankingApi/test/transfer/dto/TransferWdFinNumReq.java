package openBankingApi.test.transfer.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransferWdFinNumReq {
	String userId;
	String userName;
	String bank_tran_id;			//F123456789U4BC34239Z
	String cntr_account_type;		//N (계좌)
	String cntr_account_num;		//00012345678901234
	String dps_print_content;		//쇼핑몰환불
	String fintech_use_num;			//123456789012345678901234
	String wd_print_content;		//오픈뱅킹출금
	String tran_amt;				//10000
	String tran_dtime;				//20190910101921
	String req_client_name;			//홍길동
	String req_client_bank_code;	//097
	String req_client_account_num;	//1101230000678
//	String req_client_fintech_use_num; //요청 고객 핀테크 이용번호
	String req_client_num;			//HONGGILDONG1234
	String transfer_purpose;		//TR
	//String sub_frnc_name;			//하위가맹점
	//String sub_frnc_num;			//123456789012
	//String sub_frnc_business_num;	//1234567890
	String recv_client_name;		//김오픈
	String recv_client_bank_code;	//097
	String recv_client_account_num;	//232000067812
}