package pl.zaprogramuj.spring.boot.webapp.domain.page;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PAGE_CHARACTERISTICS")
public class PageCharacteristics
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	
	private String uriAddress;
	
	private String additionalCSS;

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getUriAddress()
	{
		return uriAddress;
	}

	public void setUriAddress(String uriAddress)
	{
		this.uriAddress = uriAddress;
	}

	public String getAdditionalCSS()
	{
		return additionalCSS;
	}

	public void setAdditionalCSS(String additionalCSS)
	{
		this.additionalCSS = additionalCSS;
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((additionalCSS == null) ? 0 : additionalCSS.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((uriAddress == null) ? 0 : uriAddress.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PageCharacteristics other = (PageCharacteristics) obj;
		if (additionalCSS == null)
		{
			if (other.additionalCSS != null)
				return false;
		} else if (!additionalCSS.equals(other.additionalCSS))
			return false;
		if (id != other.id)
			return false;
		if (uriAddress == null)
		{
			if (other.uriAddress != null)
				return false;
		} else if (!uriAddress.equals(other.uriAddress))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "PageCharacteristics [id=" + id + ", urlAddress=" + uriAddress + ", additionalCSS=" + additionalCSS
				+ "]";
	}
}
