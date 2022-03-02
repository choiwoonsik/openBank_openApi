package openBankingApi.test.account.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountBalanceDto {
	String api_tran_id;
	String api_tran_dtm;
	String rsp_code;
	String rsp_message;
	String bank_tran_id;
	String bank_tran_date;
	String bank_code_tran;
	String bank_rsp_code;
	String bank_rsp_message;
	String bank_name;
	String savings_bank_name;
	String fintech_use_num;
	String balance_amt;
	String available_amt;
	String account_type; //1:수시입출금,2:예적금, 6:수익증권,T:종합계좌
	String product_name;
	String account_issue_date;
	String maturity_date;
	String last_tran_date;
}
