package com.example.android.ver13.data;

import android.provider.BaseColumns;


public final class Contract {
    private Contract() {
    }

    public static final class ContractNames implements BaseColumns {
        public final static String TABLE_NAME = "statistic";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NAME = "fullName";
        public final static String COLUMN_TYPE = "typePoint";
        public final static String COLUMN_NUMBER = "number";
        public final static String COLUMN_MATCH_MODE = "matchMode";
        public final static String COLUMN_COURT_SURFACE = "courtSurface";
        public final static String COLUMN_DAY = "day";
        public final static String COLUMN_MONTH = "month";
        public final static String COLUMN_YEAR = "year";
        public final static String COLUMN_MATCH_NUMBER = "matchNumber";
    }

    public static final class StatTableSave implements BaseColumns {
        public final static String STATISTIC_SAVE = "statistic_SAVE";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_A_NAME_SAVE = "full_A_NameSAVE";
        public final static String COLUMN_B_NAME_SAVE = "full_B_NameSAVE";
        public final static String COLUMN_NUM_NUM_st = "numnumST";
        public final static String COLUMN_KTBFPS_st = "ktbfpsST";
        public final static String COLUMN_KTBSPS_st = "ktbspsST";
        public final static String COLUMN_SET1A_st = "set1aST";
        public final static String COLUMN_SET1A_st_tb = "set1aST_tb";
        public final static String COLUMN_SET1B_st = "set1bST";
        public final static String COLUMN_SET1B_st_tb = "set1bST_tb";
        public final static String COLUMN_SET2A_st = "set2aST";
        public final static String COLUMN_SET2A_st_tb = "set2aST_tb";
        public final static String COLUMN_SET2B_st = "set2bST";
        public final static String COLUMN_SET2B_st_tb = "set2bST_tb";
        public final static String COLUMN_SET3A_st = "set3aST";
        public final static String COLUMN_SET3A_st_tb = "set3aST_tb";
        public final static String COLUMN_SET3B_st = "set3bST";
        public final static String COLUMN_SET3B_st_tb = "set3bST_tb";
        public final static String COLUMN_SET4A_st = "set4aST";
        public final static String COLUMN_SET4A_st_tb = "set4aST_tb";
        public final static String COLUMN_SET4B_st = "set4bST";
        public final static String COLUMN_SET4B_st_tb = "set4bST_tb";
        public final static String COLUMN_SET5A_st = "set5aST";
        public final static String COLUMN_SET5A_st_tb = "set5aST_tb";
        public final static String COLUMN_SET5B_st = "set5bST";
        public final static String COLUMN_SET5B_st_tb = "set5bST_tb";
        public final static String COLUMN_A_Winner = "A_winner";
        public final static String COLUMN_B_Winner = "B_winner";
        public final static String COLUMN_A_Ace = "A_ace";
        public final static String COLUMN_B_Ace = "B_ace";
        public final static String COLUMN_A_UE = "A_ue";
        public final static String COLUMN_B_UE = "B_ue";
        public final static String COLUMN_A_DE = "A_de";
        public final static String COLUMN_B_DE = "B_de";
        public final static String COLUMN_MM = "_match_mode";
        public final static String COLUMN_COURT_TYPE = "_court_type";
        public final static String COLUMN_ADD = "ADD_save";
        public final static String COLUMN_A_NAME_1 = "name_A_1";
        public final static String COLUMN_A_NAME_2 = "name_A_2";
        public final static String COLUMN_B_NAME_1 = "name_B_1";
        public final static String COLUMN_B_NAME_2 = "name_B_2";
        public final static String COLUMN_DATE = "date";
        public final static String COLUMN_TB1PL = "tb1pl";
        public final static String COLUMN_TB2PL = "tb2pl";
        public final static String COLUMN_TIE_BREAK_DONE = "tieBreakDone";
        public final static String COLUMN_STOP_MATCH_IS_ENABLED = "stopMatchIsEnabled";
        public final static String COLUMN_TB = "tb";
        public final static String COLUMN_1_SET_A_DONE = "_1_set_a_done";
        public final static String COLUMN_1_SET_B_DONE = "_1_set_b_done";
        public final static String COLUMN_2_SET_A_DONE = "_2_set_a_done";
        public final static String COLUMN_2_SET_B_DONE = "_2_set_b_done";
        public final static String COLUMN_3_SET_A_DONE = "_3_set_a_done";
        public final static String COLUMN_3_SET_B_DONE = "_3_set_b_done";
        public final static String COLUMN_4_SET_A_DONE = "_4_set_a_done";
        public final static String COLUMN_4_SET_B_DONE = "_4_set_b_done";
        public final static String COLUMN_1_SET = "_1_set";
        public final static String COLUMN_2_SET = "_2_set";
        public final static String COLUMN_3_SET = "_3_set";
        public final static String COLUMN_4_SET = "_4_set";
        public final static String COLUMN_5_SET = "_5_set";
        public final static String COLUMN_1_SET_TB_DONE = "_1_set_tb_done";
        public final static String COLUMN_2_SET_TB_DONE = "_2_set_tb_done";
        public final static String COLUMN_3_SET_TB_DONE = "_3_set_tb_done";
        public final static String COLUMN_4_SET_TB_DONE = "_4_set_tb_done";
        public final static String COLUMN_5_SET_TB_DONE = "_5_set_tb_done";
        public final static String COLUMN_MATCH_DONE = "match_done";
        public final static String COLUMN_MATCH_STATUS = "match_status";

    }


    public static final class UndoNumbers implements BaseColumns {
        public final static String TABLE_NAME_1 = "undoTable";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_NUM_NUM = "numnum";
        public final static String COLUMN_NUM_ID = "numID";
        public final static String COLUMN_KTBFPS = "ktbfps";
        public final static String COLUMN_KTBSPS = "ktbsps";
        public final static String COLUMN_FPS = "fps";
        public final static String COLUMN_SPS = "sps";
        public final static String COLUMN_SET1A = "set1a";
        public final static String COLUMN_SET1B = "set1b";
        public final static String COLUMN_SET2A = "set2a";
        public final static String COLUMN_SET2B = "set2b";
        public final static String COLUMN_SET3A = "set3a";
        public final static String COLUMN_SET3B = "set3b";
        public final static String COLUMN_SET4A = "set4a";
        public final static String COLUMN_SET4B = "set4b";
        public final static String COLUMN_SET5A = "set5a";
        public final static String COLUMN_SET5B = "set5b";
        public final static String COLUMN_tbQ = "tbQ";
    }
}
