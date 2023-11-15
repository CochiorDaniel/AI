import numpy as np


def relu(z):
    return np.maximum(0, z)


def relu_derivative(z):
    if z > 0:
        return 1
    else:
        return 0


def mean_squared_error(y_actual, y_predicted):
    return ((y_actual - y_predicted) ** 2).mean()


def softmax(scores):
    exp_scores = np.exp(scores)
    probabilities = exp_scores / np.sum(exp_scores, axis=0)
    return probabilities
