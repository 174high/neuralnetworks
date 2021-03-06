package com.github.neuralnetworks.calculation.neuronfunctions;

import java.util.SortedMap;

import com.github.neuralnetworks.architecture.GraphConnections;
import com.github.neuralnetworks.architecture.Layer;
import com.github.neuralnetworks.calculation.ConnectionCalculator;
import com.github.neuralnetworks.calculation.ValuesProvider;

/**
 * Soft Rectified linear unit
 */
public class AparapiSoftReLU extends ConnectionCalculatorFullyConnected {

    private static final long serialVersionUID = -6602713983386107132L;

    @Override
    protected ConnectionCalculator createInputFunction(SortedMap<GraphConnections, Integer> inputConnections, ValuesProvider valuesProvider, Layer targetLayer) {
	return new AparapiSoftReLUFunction(inputConnections, valuesProvider.getColumns(), targetLayer);
    }

    public static class AparapiSoftReLUFunction extends AparapiWeightedSum {

	private static final long serialVersionUID = 2572354641295173835L;

	public AparapiSoftReLUFunction(SortedMap<GraphConnections, Integer> inputConnections, int miniBatchSize, Layer targetLayer) {
	    super(inputConnections, miniBatchSize, targetLayer);
	}

	@Override
	protected void after() {
	    int mb = miniBatchSize;
	    int outputId = getGlobalId() * mb;
	    
	    for (int i = 0; i < mb; i++) {
		output[outputId + i] = log(1 + exp(output[outputId + i]));
	    }
	}
    }
}
