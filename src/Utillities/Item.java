package Utillities;

public class Item<V> implements Comparable<Item>
{
    private V key;
    private String value;

    //Constructor
    public Item(V key, String value)
    {
        this.key = key;
        this.value = value;
    }

    //Verkrijgt de key
    public V getValue()
    {
        return key;
    }

    //Verkrijgt de value
    public String getKey()
    {
        return value;
    }

    //Compared de item aan de key.
    public int compareTo(Item item)
    {
        return getKey().compareTo(item.getKey());
    }

    @Override
    //Equals het item aan de key.
    public boolean equals(Object object)
    {
        Item item = (Item)object;

        return (item == null) ? false : key.equals(item.getValue());
    }

    @Override
    //Verkijgt de hashCode.
    public int hashCode()
    {
        return key.hashCode();
    }

    @Override
    // returned de value.
    public String toString()
    {
        return value;
    }
}
