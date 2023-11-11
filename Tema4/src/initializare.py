import numpy as np


def initializare_parametri(dim_intrare, dim_ascuns1, dim_ascuns2, dim_iesire):
    np.random.seed(42)

    w1 = np.random.randn(dim_ascuns1, dim_intrare) * np.sqrt(2 / dim_intrare)
    b1 = np.zeros((dim_ascuns1, 1)) + 0.01

    w2 = np.random.randn(dim_ascuns2, dim_ascuns1) * np.sqrt(2 / dim_ascuns1)
    b2 = np.zeros((dim_ascuns2, 1)) + 0.01

    w3 = np.random.randn(dim_iesire, dim_ascuns2) * np.sqrt(2 / dim_ascuns2)
    b3 = np.zeros((dim_iesire, 1)) + 0.01

    p = {"w1": w1, "b1": b1, "w2": w2, "b2": b2, "w3": w3, "b3": b3}

    return p


dim_str_intrare = 7
dim_str_ascuns1 = 4
dim_str_ascuns2 = 4
dim_str_iesire = 3
rata_de_invatare = 0.01
nr_maxim_epoci = 1000


parametri = initializare_parametri(dim_str_intrare, dim_str_ascuns1, dim_str_ascuns2, dim_str_iesire)
