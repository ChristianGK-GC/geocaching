package de.blafoo.geocaching.playground;

public class OCR {
	
	public void createLongNumer() {
        final long nord = 24431L;
        final long ost = 48116L;
       
        long summe = 0;
        int stellen = 0;
        do {
            int next = de.blafoo.util.Math.randInt(5,6);
            System.out.print(next);
            stellen++;
            summe += next;
           
        } while (summe < nord);
       
        System.out.println("Summe="+summe+" Stellen="+stellen);
    }

}
