package openBankingApi.test.user.dto;

import lombok.Data;

@Data
public class EmdAccountInfo {
	String fintech_use_num;
	String account_alias;
	String bank_code_std;
	String bank_code_sub;
	String bank_name;
	String savings_bank_name;
	String account_num;
	String account_num_masked;
	String account_seq;
	String account_holder_name;
	String account_holder_type;
	String account_type;
	String inquiry_agree_yn;
	String inquiry_agree_dtime;
	String transfer_agree_yn;
	String transfer_agree_dtime;
	String payer_num;
}
