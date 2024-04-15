package craft.tierup;

public class Calc {

    public int getMaterial(
            int baseQuantity,
            int tierMultiplier,
            int materialA
    ){
        int result = (baseQuantity * tierMultiplier) - materialA;

        if (result <= 0)
            result = 0;

        return result;
    }

    public int getMaterial(
            int baseQuantity,
            int tierMultiplier,
            int materialA,
            int materialB,
            int materialBMultiplier
    ){
        int result = (baseQuantity * tierMultiplier) - materialA - (materialB * materialBMultiplier);

        if (result <= 0)
            result = 0;

        return result;
    }

    public int getMaterial(
            int baseQuantity,
            int tierMultiplier,
            int materialA,
            int materialB,
            int materialBMultiplier,
            int materialC,
            int materialCMultiplier
    ){
        int result = (baseQuantity * tierMultiplier) - materialA - (materialB * materialBMultiplier) - (materialC * materialCMultiplier);

        if (result <= 0)
            result = 0;

        return result;
    }

    public int getMaterialD(int baseQuantity, int materialD){
        return baseQuantity - materialD;
    }

    public long getCopperCost(
        long minCostA,
        long resourcesA,
        long resourcesB,
        long resourcesC
    ){
        return (minCostA * resourcesA) - resourcesB - resourcesC;
    }

    public long getCopperCost(
        long minCostA,
        long resourcesA,
        long minCostB,
        long resourcesB,
        long resourcesC,
        long resourcesD
    ){
        return (minCostA * resourcesA) + (minCostB * resourcesB) - resourcesC - resourcesD;
    }

    public long getCopperCost(
        long minCostA,
        long resourcesA,
        long minCostB,
        long resourcesB,
        long minCostC,
        long resourcesC,
        long resourcesD,
        long resourcesE
    ){
        return (minCostA * resourcesA) + (minCostB * resourcesB) + (minCostC * resourcesC) - resourcesD - resourcesE;
    }

    public long getCopperCost(
        long minCostA,
        long resourcesA,
        long minCostB,
        long resourcesB,
        long minCostC,
        long resourcesC,
        long minCostD,
        long resourcesD,
        long resourcesE,
        long resourcesF
    ){
        return (minCostA * resourcesA) + (minCostB * resourcesB) + (minCostC * resourcesC) + (minCostD * resourcesD) - resourcesE - resourcesF;
    }

    public long getCopperCost(
        long minCostA,
        long resourcesA,
        long minCostB,
        long resourcesB,
        long minCostC,
        long resourcesC,
        long minCostD,
        long resourcesD,
        long minCostE,
        long resourcesE,
        long minCostF,
        long resourcesF,
        long resourcesG,
        long resourcesH
    ){
        return (minCostA * resourcesA) + (minCostB * resourcesB) + (minCostC * resourcesC) + (minCostD * resourcesD) + (minCostE * resourcesE) + (minCostF * resourcesF) - resourcesG - resourcesH;
    }

    public long getDarksteelOrGlitteringCost(
        long minCostA,
        long resourcesA,
        long resourcesB
    ){
        return (minCostA * resourcesA) - resourcesB;
    }

    public long getDarksteelOrGlitteringCost(
        long minCostA,
        long resourcesA,
        long minCostB,
        long resourcesB,
        long resourcesC
    ){
        return (minCostA * resourcesA) + (minCostB * resourcesB) - resourcesC;
    }

    public long getDarksteelOrGlitteringCost(
        long minCostA,
        long resourcesA,
        long minCostB,
        long resourcesB,
        long minCostC,
        long resourcesC,
        long resourcesD
    ){
        return (minCostA * resourcesA) + (minCostB * resourcesB) + (minCostC * resourcesC) - resourcesD;
    }

    public long getDarksteelOrGlitteringCost(
        long minCostA,
        long resourcesA,
        long minCostB,
        long resourcesB,
        long minCostC,
        long resourcesC,
        long minCostD,
        long resourcesD,
        long resourcesE
    ){
        return (minCostA * resourcesA) + (minCostB * resourcesB) + (minCostC * resourcesC) + (minCostD * resourcesD) - resourcesE;
    }

    public long getDarksteelOrGlitteringCost(
        long minCostA,
        long resourcesA,
        long minCostB,
        long resourcesB,
        long minCostC,
        long resourcesC,
        long minCostD,
        long resourcesD,
        long minCostE,
        long resourcesE,
        long minCostF,
        long resourcesF,
        long resourcesG
    ){
        return (minCostA * resourcesA) + (minCostB * resourcesB) + (minCostC * resourcesC) + (minCostD * resourcesD) + (minCostE * resourcesE) + (minCostF * resourcesF) - resourcesG;
    }

    public long getElixirCost(long minCost, long resourcesA, long materialB){
        return minCost * resourcesA - materialB;
    }

    public long getElixirCost(
            long minCostA,
            long resourcesA,
            long minCostB,
            long resourcesB,
            long resourcesC
    ){
        return (minCostA * resourcesA) + (minCostB * resourcesB) - resourcesC;
    }

    public long getElixirCost(
            long minCostA,
            long resourcesA,
            long minCostB,
            long resourcesB,
            long minCostC,
            long resourcesC,
            long resourcesD
    ){
        return(minCostA * resourcesA) + (minCostB * resourcesB) + (minCostC * resourcesC) - resourcesD;
    }

    public long getDragonSteelCost(long minCost, long material, long resources){
        return minCost * material - resources;
    }
//
    public long getEquipmentCost(long resources, long tierMultiplier){
        return resources * tierMultiplier;
    }

    public long getTotalCost(long materialCost){

        if (materialCost < 0)
            materialCost = 0;

        return materialCost;
    }

    public long getTotalCost(long materialCost, long equipmentCost){
        long result = materialCost + equipmentCost;

        if (result <= 0)
            result = 0;

        return result;
    }

}
