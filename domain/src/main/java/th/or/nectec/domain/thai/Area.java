/*
 * Copyright © 2015 NECTEC
 *   National Electronics and Computer Technology Center, Thailand
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package th.or.nectec.domain.thai;

public class Area {

    public static final int SQUARE_METER_PER_RAI = 1600;
    public static final int SQUARE_METER_PER_NGAN = 400;
    public static final float SQUARE_METER_PER_SQUARE_WA = 4;

    private int sizeSquareMeter;

    private int rai;
    private int ngan;
    private int squareWa;


    public Area(int size) {
        this.sizeSquareMeter = size;

        extractToRaiNganSquareWa();
        roundRaiNganSqaureWa();
    }

    private void extractToRaiNganSquareWa() {
        rai = squareMeterToRai(sizeSquareMeter);
        ngan = squareMeterToNgan(sizeSquareMeter);
        squareWa = squareMeterToSquareWa(sizeSquareMeter);
    }

    private void roundRaiNganSqaureWa() {
        if(squareWa == 100) {
            ngan++;
            squareWa = 0;
        }
        if(ngan == 4) {
            rai++;
            ngan = 0;
        }
    }

    public int getSquareMeter(){
        return sizeSquareMeter;
    }

    public int getRai(){
        return rai;
    }

    public int getNgan(){
        return ngan;
    }

    public int getSquareWa(){
        return squareWa;
    }

    public String prettyPrint(){
       return new PrettyPrinter().print();
    }

    public static Area fromSquareMeter(int sqaureMeter){
        return new Area(sqaureMeter);
    }

    public static Area fromRaiNganSqaureWa(int rai, int ngan,int  squareWa){
        Area area = new Area(RaiToSqMeter(rai, ngan, squareWa));
        return area;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getRai());
        builder.append("-");
        builder.append(getNgan());
        builder.append("-");
        builder.append(getSquareWa());
        return builder.toString();
    }

    public static int RaiToSqMeter(int rai, int ngan, int tarangwa) {
        float sqMeter = (rai * SQUARE_METER_PER_RAI) + (ngan * SQUARE_METER_PER_NGAN) + (tarangwa * SQUARE_METER_PER_SQUARE_WA);
        return Math.round(sqMeter);
    }

    public static int squareMeterToRai(int squareMeter) {
        int rai = squareMeter / SQUARE_METER_PER_RAI;
        return rai;
    }

    public static int squareMeterToNgan(int squareMeter) {
        int ngan = (squareMeter % SQUARE_METER_PER_RAI) / SQUARE_METER_PER_NGAN;
        return ngan;
    }

    public static int squareMeterToSquareWa(int squareMeter) {
        float squareWa = (squareMeter % SQUARE_METER_PER_NGAN) / SQUARE_METER_PER_SQUARE_WA;
        return Math.round(squareWa);
    }

    private  class PrettyPrinter{

        public String print(){
            StringBuilder builder = new StringBuilder();
            if(rai > 0){
                builder.append(rai);
                builder.append(" "+ "ไร่");
            }
            if(ngan > 0){
                builder.append(" ");
                builder.append(ngan);
                builder.append(" "+ "งาน");
            }
            if(squareWa > 0){
                builder.append(" ");
                builder.append(squareWa);
                builder.append(" " + "ตารางวา");
            }
            return builder.toString().trim();
        }
    }
}
