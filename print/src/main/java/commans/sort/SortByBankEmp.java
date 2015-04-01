package commans.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import model.entity.FirePolicyEntity;
/**
 * 
 *將policy注入此類別後排序再取回來 
 * 
 **/
public class SortByBankEmp implements Comparator<Object>
{
    private List<FirePolicyEntity> policys = new ArrayList<FirePolicyEntity>();

    public List<FirePolicyEntity> getPolicys()
    {
        Collections.sort(policys,new SortByBankEmp());
        return policys;
    }

    public void setPolicys(List<FirePolicyEntity> policys)
    {
        this.policys = policys;
    }

    @Override
    public int compare(Object o1, Object o2)
    {
        FirePolicyEntity FirePolicy0 = (FirePolicyEntity)o1;
        FirePolicyEntity FirePolicy1 = (FirePolicyEntity)o2;
        //單位
        int i = FirePolicy0.getNEWPLY().substring(0,1).compareTo(FirePolicy1.getNEWPLY().substring(0,1));
        if(i!=0)
	  return i;
        //經手人
        i = FirePolicy0.getEMPA().compareTo(FirePolicy1.getEMPA());
        if(i!=0)
	  return i;
        //外部服務員
        i = FirePolicy0.getAGENTEMPNO().compareTo(FirePolicy1.getAGENTEMPNO());
        if(i!=0)
	  return i;
        //保單號
        return FirePolicy0.getNEWPLY().compareTo(FirePolicy1.getNEWPLY());
    }
}
