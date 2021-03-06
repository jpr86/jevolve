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
import org.apache.logging.log4j.*;

/**
 *  Implementation of a standard Gaussian Mutator. If an individual has a
 *  positive mutation rate (>0), then for each locus throw the dice. If the dice are
 *  less than the individual's mutation rate, replace the value at that locus
 *  with its old value + a random gaussian value, using the sigma for that gene
 *  as the standard deviation.
 *
 * @param E gene type.
 * @author Jeff Ridder
 */
public class GaussianMutator<E> extends Mutator
{
    private final static Logger logger =
        LogManager.getLogger(GaussianMutator.class);

    /**
     * Mutates the individual using Gaussian mutation.
     *
     * @param  ind individual to be mutated.
     */
    public void mutate(Individual ind)
    {
        if (ind.getProbMutation() > 0)
        {
            GAIndividual<E> ga_ind = (GAIndividual<E>) ind;

            logger.debug("Ga Ind Genotype Size: " + ga_ind.getGenotype().size());
            for (int i = 0; i < ga_ind.getGenotype().size(); i++)
            {
                if (MersenneTwisterFast.getInstance().nextDouble() <=
                    ind.getProbMutation())
                {
                    ga_ind.setEvaluated(false);
                    ga_ind.setValue(i,
                        (ga_ind.getGenome().get(i)).randomGaussianValue(ga_ind.getValue(i)));
                }
            }
        }
    }
}
