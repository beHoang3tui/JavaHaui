package KTTX2Java;

import java.util.*;

public class HousingManagerImpl implements IHousingManager {

	@Override
	public boolean addHousing(Housing h) {
		// TODO Auto-generated method stub
		if(checkID(h.getProduct_id())) {
			System.out.println("Invalid Product ID");
			return false;
		}
		RunMain.housings.add(h);
		return true;
	}

	@Override
	public boolean editHousing(Housing h) {
		// TODO Auto-generated method stub
		if(!checkID(h.getProduct_id())) {
			System.out.println("Not Found");
			return false;
		}
		for(Housing hs : RunMain.housings) {
			if(hs.getProduct_id().compareTo(h.getProduct_id()) == 0) {
				hs.setProduct_id(h.getProduct_id());
				hs.setProduct_name(h.getProduct_name());
				hs.setProduct_price(h.getProduct_price());
				hs.setProduct_total(h.getProduct_total());
				hs.setFeatured(h.getFeatured());
				hs.setArea(h.getArea());
			}
		}
		return true;
	}

	@Override
	public boolean delHousing(Housing h) {
		// TODO Auto-generated method stub
		if(!checkID(h.getProduct_id())) {
			System.out.println("Not Found");
			return false;
		}
		
		for(int index = 0; index < RunMain.housings.size(); index++) {
			if(RunMain.housings.get(index).getProduct_id().compareTo(h.getProduct_id()) == 0) {
				RunMain.housings.remove(index);
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Housing> searchHousing(String name) {
		// TODO Auto-generated method stub
		List<Housing> tmp = new ArrayList<>();
		
		for (Housing hs : RunMain.housings) {
			if(hs.getProduct_name().toLowerCase().contains(name.toLowerCase())) {
				tmp.add(hs);
			}
		}
		return tmp;
	}

	@Override
	public List<Housing> sortedHousing(List<Housing> hs, boolean isINC) {
		// TODO Auto-generated method stub
		if(isINC) {
			Collections.sort(hs , new sortedByPrice());
		}
		else {
			Collections.sort(hs , new sortedByPrice().reversed());
		}
		return hs ;
	}
	
	public boolean checkID(String product_id) {
		for(Housing hs: RunMain.housings) {
			if(hs.getProduct_id().compareTo(product_id) == 0) {
				return true;
			}
		}
		return false;
	}
	
	public Housing find(String product_id) {
		for(Housing hs : RunMain.housings) {
			if(hs.getProduct_id().compareTo(product_id) == 0) {
				return (hs);
			}
		}
		return null;
	}
}

class sortedByPrice implements Comparator<Housing>{
	@Override
	public int compare(Housing hs1, Housing hs2) {
		return (int)(hs1.getProduct_price() - hs2.getProduct_price());
	}
}


