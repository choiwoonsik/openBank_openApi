package openBankingApi.test.account.dto;

import lombok.Data;

@Data
public class TransactionListReq {
	String userId;
	String bank_tran_id;
	String fintech_use_num;
	String inquiry_type;
	String inquiry_base;
	String from_date;
	String from_time;
	String to_date;
	String to_time;
	String sort_order;
	String tran_dtime;
	String befor_inquiry_trace_info;
}
