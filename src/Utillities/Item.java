package Utillities;

public class Item<V> implements Comparable<Item>
{
    private V key;
    private String value;

    public Item(V key, String value)
    {
        this.key = key;
        this.value = value;
    }

    public V getValue()
    {
        return key;
    }

    public String getKey()
    {
        return value;
    }

    public int compareTo(Item item)
    {
        return getKey().compareTo(item.getKey());
    }

    @Override
    public boolean equals(Object object)
    {
        Item item = (Item)object;

        return (item == null) ? false : key.equals(item.getValue());
    }

    @Override
    public int hashCode()
    {
        return key.hashCode();
    }

    @Override
    public String toString()
    {
        return value;
    }
}
