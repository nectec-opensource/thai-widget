/*
 * Copyright (c) 2016 NECTEC
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
 *
 */

package th.or.nectec.thai.unit;

public class AreaConverter {

    public static final int SQUARE_METER_PER_RAI = 1600;
    public static final int SQUARE_METER_PER_NGAN = 400;
    public static final float SQUARE_METER_PER_SQUARE_WA = 4;

    public static int RaiToSqMeter(int rai, int ngan, int tarangwa) {
        float sqMeter = (rai * SQUARE_METER_PER_RAI)
                + (ngan * SQUARE_METER_PER_NGAN)
                + (tarangwa * SQUARE_METER_PER_SQUARE_WA);
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
}
