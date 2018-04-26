package com.hw.vo;

public class Correlation {
   
    String main; 
    int total_num;
    int total_area;
    int score;
   public Correlation() {
	   
   }
	public Correlation(String main, int total_num, int total_area, int score) {
		this.main = main;
		this.total_num = total_num;
		this.total_area = total_area;
		this.score = score;
	}
   public String getMain() {
      return main;
   }
   public void setMain(String main) {
      this.main = main;
   }
   public int getScore() {
      return score;
   }
   public void setScore(int score) {
      this.score = score;
   }
   public int getTotal_num() {
      return total_num;
   }
   public void setTotal_num(int total_num) {
      this.total_num = total_num;
   }
   public int getTotal_area() {
      return total_area;
   }
   public void setTotal_area(int total_area) {
      this.total_area = total_area;
   }
    
    
    

}
