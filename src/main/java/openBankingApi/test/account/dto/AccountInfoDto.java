package openBankingApi.test.account.dto;

import lombok.Data;

@Data
public class AccountInfoDto {
	String fintech_use_num;				// 123456789012345678901234
	String account_alias;               // 급여계좌
	String bank_code_std;				// 097
	String bank_code_sub;               // 1230001
	String bank_name;                   // 오픈은행
	String account_num_masked;          // 0001230000-***
	String account_holder_name;         // 홍길동
	String account_holder_type;         // P
	String account_type;                // 1
	String inquiry_agree_yn;            // Y
	String inquiry_agree_dtime;         // 20190910101921
	String transfer_agree_yn;           // Y
	String transfer_agree_dtime;        // 20190910101921
	String account_state;               // 01
}
