package KTTX2Java;

import java.util.List;

public interface IHousingManager {
	
	public boolean addHousing(Housing h);
	public boolean editHousing(Housing h); 
	public boolean delHousing(Housing h);
	
	public List< Housing> searchHousing(String name);
	public List<Housing> sortedHousing(List<Housing> hs, boolean isINC); 
}
