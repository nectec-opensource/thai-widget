/*
 * Copyright (c) 2015 NECTEC
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

/**
 * Class to represent size of area in Thai unit of measurement called as
 * Rai, Ngan and Wa<sup>2</sup>
 */
public class Area {

    public static final int SQUARE_METER_PER_RAI = 1600;
    public static final int SQUARE_METER_PER_NGAN = 400;
    public static final float SQUARE_METER_PER_SQUARE_WA = 4;
    public static final String RAI = "ไร่";
    public static final String NGAN = "งาน";
    public static final String SQUARE_WA = "ตารางวา";

    private int sizeSquareMeter;

    private int rai;
    private int ngan;
    private int squareWa;


    public Area(int size) {
        this.sizeSquareMeter = size;

        extractToRaiNganSquareWa();
        roundRaiNganSqaureWa();
    }

    public static Area fromSquareMeter(int sqaureMeter) {
        return new Area(sqaureMeter);
    }

    public static Area fromRaiNganSqaureWa(int rai, int ngan, int squareWa) {
        return new Area(RaiToSqMeter(rai, ngan, squareWa));
    }

    public static int RaiToSqMeter(int rai, int ngan, int tarangwa) {
        float sqMeter = (rai * SQUARE_METER_PER_RAI) + (ngan * SQUARE_METER_PER_NGAN) + (tarangwa * SQUARE_METER_PER_SQUARE_WA);
        return Math.round(sqMeter);
    }

    public static int squareMeterToRai(int squareMeter) {
        return squareMeter / SQUARE_METER_PER_RAI;
    }

    public static int squareMeterToNgan(int squareMeter) {
        return (squareMeter % SQUARE_METER_PER_RAI) / SQUARE_METER_PER_NGAN;
    }

    public static int squareMeterToSquareWa(int squareMeter) {
        float squareWa = (squareMeter % SQUARE_METER_PER_NGAN) / SQUARE_METER_PER_SQUARE_WA;
        return Math.round(squareWa);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Area area = (Area) o;
        return rai == area.rai && ngan == area.ngan && squareWa == area.squareWa;
    }

    @Override
    public int hashCode() {
        int result = rai;
        result = 31 * result + ngan;
        result = 31 * result + squareWa;
        return result;
    }

    private void extractToRaiNganSquareWa() {
        rai = squareMeterToRai(sizeSquareMeter);
        ngan = squareMeterToNgan(sizeSquareMeter);
        squareWa = squareMeterToSquareWa(sizeSquareMeter);
    }

    private void roundRaiNganSqaureWa() {
        if (squareWa == 100) {
            ngan++;
            squareWa = 0;
        }
        if (ngan == 4) {
            rai++;
            ngan = 0;
        }
    }

    public int getSquareMeter() {
        return sizeSquareMeter;
    }

    public int getRai() {
        return rai;
    }

    public int getNgan() {
        return ngan;
    }

    public int getSquareWa() {
        return squareWa;
    }

    public String prettyPrint() {
        return new PrettyPrinter().print();
    }

    @Override
    public String toString() {
        return String.valueOf(getRai()) + "-" + getNgan() + "-" + getSquareWa();
    }

    private class PrettyPrinter {

        public static final String SPACE = " ";
        private StringBuilder stringBuilder;

        public String print() {
            stringBuilder = new StringBuilder();
            appendRai();
            appendNgan();
            appendSquareWa();
            return stringBuilder.toString().trim();
        }

        private void appendSquareWa() {
            if (squareWa > 0) {
                stringBuilder.append(SPACE).append(squareWa).append(SPACE).append(SQUARE_WA);
            }
        }

        private void appendNgan() {
            if (ngan > 0) {
                stringBuilder.append(SPACE).append(ngan).append(SPACE).append(NGAN);
            }
        }

        private void appendRai() {
            if (rai > 0) {
                stringBuilder.append(rai).append(SPACE).append(RAI);
            }
        }
    }
}
