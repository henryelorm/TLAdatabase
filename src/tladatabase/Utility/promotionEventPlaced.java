/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tladatabase.Utility;

/**
 *
 * @author Elorm
 */
public class promotionEventPlaced {

    private static boolean promoEventPlaced = false;

    public static boolean isPromoEventPlaced() {
        return promoEventPlaced;
    }

    public static void setPromoEventPlaced(boolean promoEventPlaced) {
        promotionEventPlaced.promoEventPlaced = promoEventPlaced;
    }

}
