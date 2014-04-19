package is.mjuk.cache;

import java.lang.StringBuilder;

public class Block {
    private boolean validity;
    private int tag;
    private int updated;

    /**
    * Initializes an empty block
    */
    public Block()
    {
        this.validity = false;
        this.tag = 0x00000000;
    }

    public boolean isValid(int tag)
    {
        if (this.tag == tag && this.validity == true)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    public void setData(int tag, boolean validity)
    {
        this.validity = validity;
        this.tag = tag;
    }

    public void setValidity(boolean validity)
    {
        this.validity = validity;
    }

    public void setTag(int tag)
    {
        setData(tag, true);
    }

    public String toString()
    {
        StringBuilder rv = new StringBuilder();
        rv.append(this.validity);
        rv.append(" ");
        rv.append(this.tag);
        return rv.toString();
    }
}