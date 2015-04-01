package commans.function;

import java.util.ArrayList;
import java.util.List;

import model.entity.FirePolicyEntity;


/*
 * 
 * 將保單分成A、W、G單三種
 * 
 * 
 * */
public class SplitPolicy
{
    private List<FirePolicyEntity> plys;

    public SplitPolicy(List<FirePolicyEntity> policys)
    {
        this.plys = policys;
    }

    public List<FirePolicyEntity> GetApolicy()
    {
        List<FirePolicyEntity> PolicyA = new ArrayList<FirePolicyEntity>();
        for(FirePolicyEntity ply : plys)
        {
	  if(ply.getNEWPLY().substring(4,5).equals("A"))
	  {
	      PolicyA.add(ply);
	  }
        }
        return PolicyA;
    }

    public List<FirePolicyEntity> GetWpolicy()
    {
        List<FirePolicyEntity> PolicyW = new ArrayList<FirePolicyEntity>();
        for(FirePolicyEntity ply : plys)
        {
	  if(ply.getNEWPLY().substring(4,5).equals("W"))
	  {
	      PolicyW.add(ply);
	  }
        }
        return PolicyW;
    }

    public List<FirePolicyEntity> GetGpolicy()
    {
        List<FirePolicyEntity> PolicyG = new ArrayList<FirePolicyEntity>();
        for(FirePolicyEntity ply : plys)
        {
	  if(ply.getNEWPLY().substring(4,5).equals("G"))
	  {
	      PolicyG.add(ply);
	  }
        }
        return PolicyG;
    }
}
