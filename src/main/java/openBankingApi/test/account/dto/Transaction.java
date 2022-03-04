package openBankingApi.test.account.dto;

import lombok.Data;

@Data
public class Transaction {
	String tran_date;    		//20190910
	String tran_time;    		//113000
	String inout_type;    		//입금
	String tran_type;    		//현금
	String printed_content;    	//통장인자내용
	String tran_amt;    		//450000
	String after_balance_amt;   //1000000
	String branch_name;    		// 분당점
}
