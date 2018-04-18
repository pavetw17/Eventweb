package BusinessLogic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Part;

public class FunctionAll {
	
	public double round( double value, int fractionDigits)
	{
	    double d = Math.pow( 10, fractionDigits ) ;
	    return( Math.round( value * d ) / d ) ;
	}
	
	/*
	 * fractionDigits=0
	 */
	public String round( double value)
	{
	    long d = Math.round(value);
	    String rc="";
	    if(d==0) return "0";
	    
	    //xu ly phan nguyen
	    while(d!=0)
	    {
	    	long r1 = d%10;//phan du
	    	d = d/10;//phan nguyen
	    	rc = String.valueOf(r1) + rc;
	    }
	    
	    return rc;
	}
	
	
	
	
	/*
	 * strDate: dd/MM/yyyy
	 */
	public int DoiNgayThangRaSoNguyen(String strDate)
	{
		String[] arr = strDate.split("-");
		int m_Ngay = Integer.parseInt(arr[0]);
		int m_Thang = Integer.parseInt(arr[1]);
		int m_Nam = Integer.parseInt(arr[2]);
		
		if(m_Nam<2000)return -1;
		
		int vVectorDayOfMonth[] = {31,28,31,30,31,30,31,31,30,31,30,31};
		int m_ChuKy = (m_Nam - 2000)/4 ;
		
		if(m_Nam%4 ==0 )m_ChuKy--;
		
		int m_IntNgay = m_ChuKy*(365*3+366)+(( m_Nam -1)%4)*365;  
		
		int m_NgayOfThang =0;
		for(int i=0; i<m_Thang-1; i++)
		{
			m_NgayOfThang += vVectorDayOfMonth[i];  
			
		}
		if(m_Nam%4 ==0 && m_Thang >2) m_NgayOfThang ++; 
	
		m_IntNgay += m_NgayOfThang + m_Ngay;
		
		return m_IntNgay;
	}
	
	public String DoiSoNguyenRaNgayThang(int GiaTri)
	{
		String rc="";
		int vVectorDayOfMonth[] = {31,28,31,30,31,30,31,31,30,31,30,31};
		int m_SoNgayMotChuKy = 365*3+366;
		int m_ChuKy = GiaTri/m_SoNgayMotChuKy;
		int m_SoDuNam = (GiaTri - m_ChuKy*m_SoNgayMotChuKy)/365;
		if(GiaTri%m_SoNgayMotChuKy ==0)
		{
			m_ChuKy--;
			m_SoDuNam = 3;
		}
		else if((GiaTri - m_ChuKy*m_SoNgayMotChuKy)%365==0)
		{
			m_SoDuNam--;
		}
		int m_SoDuNgay = GiaTri - m_ChuKy*m_SoNgayMotChuKy - m_SoDuNam*365;
		int m_TongNgayCacThang =0;
		int m_Thang =0;
		for(int i=0; i<12; i++)
		{
			if(m_SoDuNam ==3 && i==1)
			{
				if(m_SoDuNgay - m_TongNgayCacThang > 29)
				{
					m_TongNgayCacThang += 29;
					m_Thang++;
					continue;
				}
				//else
				//	break;
			}
			else if(m_SoDuNgay - m_TongNgayCacThang > vVectorDayOfMonth[i])
			{
				m_TongNgayCacThang += vVectorDayOfMonth[i];
				m_Thang++;
				continue;
			}
			break;
		}
		int m_Ngay = m_SoDuNgay - m_TongNgayCacThang;
		m_Thang += 1;
		int m_Nam = 2000 + m_ChuKy*4 + m_SoDuNam+1;
		
		if(m_Ngay<10)
			rc = "0"+String.valueOf(m_Ngay)+"-";
		else
			rc = String.valueOf(m_Ngay)+"-";
		if(m_Thang<10)
			rc = rc + "0" + String.valueOf(m_Thang)+"-";
		else
			rc = rc + String.valueOf(m_Thang)+"-";
		if(m_Nam<10)
			rc = rc + "0" + String.valueOf(m_Nam);
		else
			rc = rc + String.valueOf(m_Nam);				
		
		return rc;
	}
	
	public String DoiGiayRaThoiGian(int ntime)
	{
		String time;int temp = 0, giay = 0,phut = 0,gio = 0;
		giay = ntime%60; temp = ntime/60;
		phut = temp%60; 
		gio = temp/60;
		if(gio == 0)
		{
			if(phut == 0)
			{
				if(giay !=0){
					if(giay<10)
						time = "00:00:0" + String.valueOf(giay);
					else
						time = "00:00:" + String.valueOf(giay);
				}
				else
					time = "00:00:00";
			}
			else{
				if(giay<10 && phut<10)
					time = "00:0" + String.valueOf(phut)+":0"+String.valueOf(giay);
				else if(giay<10)
					time = "00:" + String.valueOf(phut)+":0"+String.valueOf(giay);
				else if(phut<10)
					time = "00:0" + String.valueOf(phut)+":"+String.valueOf(giay);
				else
					time = "00:" + String.valueOf(phut)+":"+String.valueOf(giay);
			}
		}
		else{
			if(gio<10)
				time = "0"+String.valueOf(gio)+":";
			else
				time = String.valueOf(gio)+":";
			if(phut<10)
				time = time + "0" + String.valueOf(phut)+":";
			else
				time = time + String.valueOf(phut)+":";
			if(giay<10)
				time = time + "0" + String.valueOf(giay);
			else
				time = time + String.valueOf(giay);
		}
		return time;
	}
	
	public double convText2Float(String money)
	{
		char  chText;
		String strTmp="";
		for(int i=0; i < money.length();i++)
		{
	 		chText = money.charAt(i);
			if((chText >= '0'  &&  chText <= '9')
				|| chText=='.')
			{
				strTmp += chText;
			}
		}
		return Double.valueOf(strTmp);
	}
	
	public String Format_Tien(double item)
	{
		double m_thapphan = item - (long)item;
		m_thapphan = round(m_thapphan,2);
		double m_phannguyen = (long)item;		

		String str_tien, str_temp;
		str_tien = "";
		String strPhanNguyen = round(m_phannguyen); 
		int length = strPhanNguyen.length();		
		while(length > 3)
		{
			str_temp = strPhanNguyen.substring(length-3, length);
			str_tien = "," + str_temp + str_tien;
			strPhanNguyen = strPhanNguyen.substring(0,length - 3);
			length = strPhanNguyen.length();		
		}
		str_tien = strPhanNguyen + str_tien;
		
		String strThapPhan = String.valueOf(m_thapphan);
		if(strThapPhan.length()>4)
			strThapPhan = strThapPhan.substring(2,4);
		else
			strThapPhan = strThapPhan.substring(2);
		int isZero = Integer.valueOf(strThapPhan);
		if(strThapPhan!="" && isZero>0)
			str_tien += "." + strThapPhan; 
		
		return str_tien;
	}
	
	
	
	public List<List<String>>  nhapmang_cungThuocTinh_conglai(List<List<String>>  dataIn,List<List<String>>  dataOut){
		List<String> src = new ArrayList<String>();
		List<String> dst = new ArrayList<String>();
		
		for(int i = 0; i <dataIn.size() ; i++){
			src =     dataIn.get(i);
			boolean kiemtra = false;
			for(int j = 0; j< dataOut.size(); j++){
				dst =  dataOut.get(j);
				if(src.get(1).equals(dst.get(1)) && src.get(2).equals(dst.get(2))     //1:ten thuoc 2:hamluong
				   && src.get(3).equals(dst.get(3))	&& src.get(4).equals(dst.get(4))  //3 so dang ky 4 : don vi
				   && src.get(6).equals(dst.get(6)) ){   // 6don gia
					Double temp_3 = Double.parseDouble(src.get(5)) + Double.parseDouble(dst.get(5)) ;
					dst.set(5, temp_3.toString());
					kiemtra = true; 
					break; 
				}
			}if(!kiemtra){
				List<String> newO = new ArrayList<String>();
				newO = src;
				dataOut.add( newO);
			}
		}
		return dataOut;
	}
	
	//convert time to String (hh:mm a)
	public String formatTimestampAsTwelveHour_string(java.sql.Time stamp){
        java.util.Date date = new Date(stamp.getTime());
        SimpleDateFormat format = new SimpleDateFormat("hh:mm a");
        //System.out.println(format.format(date));
        return format.format(date);
	}
	
	//insert time(hh:mm:ss) into database 
	public java.sql.Time formatStringAsTwelveHour_sql(String time)  {
		java.sql.Time timeValue = null;
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
		Date date;
		try {
			date = sdf.parse(time); // util, kieu dau vao la AM vd: 7:00 AM  
			timeValue = new java.sql.Time(date.getTime()); // util convert time , dau ra 07:00:00 insert CSDL
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return timeValue;
	}
	
	public String getFilename(Part part) {
	    for (String cd : part.getHeader("content-disposition").split(";")) {
	        if (cd.trim().startsWith("filename")) {
	            String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	            return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
	        }
	    }
	    return null;
	}
	
	
	 public static String getMD5(String passwordToHash) {
		 String generatedPassword = null;
		 try {
	           
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            
	            md.update(passwordToHash.getBytes());
	           
	            byte[] bytes = md.digest();
	            
	            StringBuilder sb = new StringBuilder();
	            for(int i=0; i< bytes.length ;i++)
	            {
	                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	            }
	            
	            generatedPassword = sb.toString();
	        } 
	        catch (NoSuchAlgorithmException e) 
	        {
	            e.printStackTrace();
	        }
		 return generatedPassword;
	  }
}
