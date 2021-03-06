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
 *  A discrete gene with double-valued alleles.
 *
 * @author Jeff Ridder
 */
public class DiscreteDoubleGAGene extends DiscreteGAGene<Double>
{
    /**
     *  Creates a new instance of DiscreteDoubleGAGene.
     */
    public DiscreteDoubleGAGene()
    {
    }

    /**
     *  Creates a new instance of DiscreteDoubleGAGene.
     *
     * @param  lower_bound lower bound for the gene
     * @param  upper_bound upper bound
     * @param  num_alleles number of alleles (including the upper and lower bounds).
     */
    public DiscreteDoubleGAGene(Double lower_bound, Double upper_bound,
        int num_alleles)
    {
        super(lower_bound, upper_bound);

        int num = Math.max(num_alleles, 2);

        addAllele(lower_bound);
        double delta = (upper_bound - lower_bound) / (double) (num - 1);
        for (int i = 1; i < num - 1; i++)
        {
            addAllele(lower_bound + delta * i);
        }
        addAllele(upper_bound);
    }

    /**
     *  Returns a random allele using a uniform probability distribution.
     *
     * @return random allele value.
     */
    public Double randomUniformValue()
    {
        Double value = getAllele((int) (MersenneTwisterFast.getInstance().
            nextDouble() * getNumAlleles()));

        return value;
    }

    /**
     * Returns a random allele using a uniform probability distribution.  The
     * input parameters are ignored.
     *
     * @param  value ignored.
     * @param  multiplier ignored.
     * @return  random allele value
     */
    public Double randomUniformValue(Double value, double multiplier)
    {
        return randomUniformValue();
    }

    /**
     *  Returns a random allele using a uniform probability distribution.  The
     *  "Gaussian" is ignored since it makes no sense for unordered discrete genes.
     *
     * @param  value ignored.
     * @return random allele value.
     */
    public Double randomGaussianValue(Double value)
    {
        return randomUniformValue();
    }

    /**
     * Returns a random allele using a uniform probability distribution.  The
     * input parameters are ignored.
     *
     * @param  value ignored.
     * @param  multiplier ignored.
     * @return  random allele value
     */
    public Double randomGaussianValue(Double value, double multiplier)
    {
        return randomUniformValue();
    }
}
