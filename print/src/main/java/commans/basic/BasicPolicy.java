package commans.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 
 * BasicPolicy Policy類別可繼承
 * 
 **/
public abstract class BasicPolicy<T extends BasicPolicyEntity> implements
	Comparator<Object>, Serializable
{
    private static final long serialVersionUID=4885748221686249245L;
    protected String Line=new String();
    protected InputStreamReader inFile;
    protected BufferedReader brFile;
    protected List<T> policys=new ArrayList<T>();

    abstract public List<T> getPolicy(String files) throws IOException;
}