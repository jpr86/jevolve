/* %%
 * 
 * JEvolve
 *
 * Copyright 2006 Jeff Ridder
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ridderware.jevolve;

import com.ridderware.jrandom.MersenneTwisterFast;

/**
 * An integer valued gene.
 *
 * @author Jeff Ridder
 */
public class IntegerGAGene extends GAGene<Integer>
{
    /**
     *  Constructor for the IntegerGAGene object
     */
    public IntegerGAGene()
    {
    }

    /**
     *  Constructor for the IntegerGAGene object
     *
     * @param  lower_bound lower bound.
     * @param  upper_bound upper bound.
     * @param  sigma standard deviation for Gaussian RNG.
     */
    public IntegerGAGene(Integer lower_bound, Integer upper_bound,
        Integer sigma)
    {
        super(lower_bound, upper_bound, sigma);
    }

    /**
     *  Returns a random uniform value between the upper and lower bound.
     *
     * @return    random uniform value
     */
    public Integer randomUniformValue()
    {
        Integer value = getLowerBound() + (MersenneTwisterFast.getInstance().
            nextInt(getUpperBound() - getLowerBound() + 1));

        return value;
    }

    /**
     * Returns a random uniform value between the lower and upper bound as a
     * deviation from the specified value.
     *
     * @param  value value from which the new random value deviates.
     * @param  multiplier specified to support generating the next value.
     * @return             random uniform value
     */
    public Integer randomUniformValue(Integer value, double multiplier)
    {
        if (MersenneTwisterFast.getInstance().nextDouble() < 0.5)
        {
            value += (int) (multiplier * (getUpperBound() - value));
        }
        else
        {
            value += (int) (multiplier * (getLowerBound() - value));
        }

        return value;
    }

    /**
     *  Returns a random Gaussian value deviating from the specified value.
     *
     * @param  value value from which the new random value deviates.
     * @return random Gaussian value.
     */
    public Integer randomGaussianValue(Integer value)
    {
        value += (int) (MersenneTwisterFast.getInstance().nextGaussian() *
            getSigma());

        value = Math.min(value, getUpperBound());

        value = Math.max(value, getLowerBound());

        return value;
    }

    /**
     * Returns a random Gaussian value between the lower and upper bound as a
     * deviation from the specified value.
     *
     * @param  value value from which the new random value deviates.
     * @param  multiplier specified to support generating the next value.
     * @return random Gaussian value
     */
    public Integer randomGaussianValue(Integer value, double multiplier)
    {
        value += (int) (MersenneTwisterFast.getInstance().nextGaussian() *
            getSigma() * multiplier);

        value = Math.min(value, getUpperBound());

        value = Math.max(value, getLowerBound());

        return value;
    }
}

