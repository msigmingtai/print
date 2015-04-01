package model.policy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

import model.entity.FirePolicyEntity;

import commans.basic.BasicPolicy;
import commans.function.ChangeCode;
import commans.function.UnitClass;

public class FireGPolicy extends BasicPolicy<FirePolicyEntity>
{
    private static final long serialVersionUID=1575680041327293431L;

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List getPolicy(String files) throws IOException
    {
	inFile=new InputStreamReader(new FileInputStream(files),"ISO8859_1");
	brFile=new BufferedReader(inFile);
	while ((Line=brFile.readLine())!=null)
	{
	    FirePolicyEntity ply=new FirePolicyEntity();
	    ply.setBANKKEY(Line.substring(0,40));
	    ply.setINSDNAME(new ChangeCode().ChgChCode(Line.substring(40,70),Line.substring(80,90)));
	    ply.setIDNO(Line.substring(70,80));
	    ply.setPLYNO(Line.substring(80,90));
	    ply.setEXPDAY(Line.substring(90,97));
	    ply.setINSDAMT(Line.substring(97,106));
	    ply.setFIREPREM(Line.substring(106,113));
	    ply.setEAMT(Line.substring(113,122));
	    ply.setEPREM(Line.substring(122,129));
	    ply.setBANKFLAG(Line.substring(129,130));
	    ply.setCLLATM(Line.substring(130,146));
	    ply.setCLLDAY(Line.substring(146,153));
	    ply.setAREANO(Line.substring(153,156));
	    ply.setADDR(new ChangeCode().ChgChCode(Line.substring(156,216),Line.substring(80,90)));
	    ply.setTEL(Line.substring(216,226));
	    ply.setLOCATION(new ChangeCode().ChgChCode(Line.substring(226,306),Line.substring(80,90)));
	    ply.setAREA(Line.substring(306,311));
	    ply.setCONSTMARK(Line.substring(311,315));
	    ply.setFLOOR(Line.substring(315,317));
	    ply.setCONSTCLASS(Line.substring(317,318));
	    ply.setAREASIZE(Line.substring(318,326));
	    ply.setSTRUYEAR(Line.substring(326,329));
	    ply.setAGENTUNIT(Line.substring(329,339));
	    ply.setAGENTEMPNO(Line.substring(339,350));
	    ply.setMTG(Line.substring(350,357));
	    ply.setEMPA(Line.substring(357,362));
	    ply.setEMPA1(Line.substring(362,363));
	    ply.setSTUNIT(Line.substring(363,365));
	    ply.setBROKER(Line.substring(365,367));
	    ply.setAGENT(Line.substring(367,368));
	    ply.setRATE(Line.substring(368,374));
	    ply.setBELONG2(Line.substring(374,376));
	    ply.setFASRCNAME(new ChangeCode().ChgChCode(Line.substring(376,406),""));
	    ply.setFASRIDNO(Line.substring(406,416));
	    ply.setSEMP1(Line.substring(416,421));
	    ply.setNEWPLY(Line.substring(421,431));
	    ply.setAMT2(Line.substring(431,440));
	    ply.setPREM2(Line.substring(440,447));
	    ply.setRATE2(Line.substring(447,453));
	    ply.setFSEX(Line.substring(453,454));
	    ply.setBIRTH(Line.substring(454,461));
	    ply.setFEHALF(new ChangeCode().ChgChCode(Line.substring(461,473),""));
	    ply.setRELATE(Line.substring(473,474));
	    ply.setSEX(Line.substring(474,475));
	    ply.setFBIRTH(Line.substring(475,482));
	    ply.setBEHALF(new ChangeCode().ChgChCode(Line.substring(482,494),""));
	    ply.setFLPNO(Line.substring(494,506));
	    // 取得保單號單位及歸屬
	    ply.setUnitName(new UnitClass().GetUnit(Line.substring(421,423),Line.substring(363,365)));
	    // ply.setFLPNO(null);
	    policys.add(ply);
	}
	return policys;
    }

    @Override
    public int compare(Object o1, Object o2)
    {
	FirePolicyEntity FirePolicy0=(FirePolicyEntity)o1;
	FirePolicyEntity FirePolicy1=(FirePolicyEntity)o2;

	int flag=FirePolicy0.getUnitName().compareTo(FirePolicy1.getUnitName());

	if(flag==0)
	{
	    return FirePolicy0.getSEMP1().compareTo(FirePolicy1.getSEMP1());
	}
	else
	{
	    return flag;
	}
    }

    public List<FirePolicyEntity> SortPolicy()
    {
	Collections.sort(policys,new FireGPolicy());
	return policys;
    }

}
