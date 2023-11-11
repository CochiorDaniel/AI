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



