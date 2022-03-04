package openBankingApi.test.account.dto;

import lombok.Data;

import java.util.List;

@Data
public class TransactionListRes {
	String api_tran_id;                    //2ffd133a-d17a-431d-a6a5",
	String api_tran_dtm;                    //20190910101921567",
	String rsp_code;                    //A0000",
	String rsp_message;                    //""
	String bank_tran_id;                    //F123456789U4BC34239Z",
	String bank_tran_date;                    //20190910",
	String bank_code_tran;                    //097",
	String bank_rsp_code;                    //000",
	String bank_rsp_message;                    //
	String bank_name;                    //오픈은행",
	String fintech_use_num;                    //123456789012345678901234",
	String balance_amt;                    //1000000",
	String page_record_cnt;                    //25",
	String next_page_yn;                    //Y",
	String befor_inquiry_trace_info;                    //1T201806171",
	List<Transaction> res_list;
}
