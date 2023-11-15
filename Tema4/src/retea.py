from initializare import initializare_parametri
import functii as f
import numpy as np


class RN:
    def __init__(self):
        self.dimensiune_intrare = 7
        self.dimensiune_ascuns1 = 4
        self.dimensiune_ascuns2 = 4
        self.dimensiune_iesire = 3
        self.parametri = initializare_parametri(self.dimensiune_intrare, self.dimensiune_ascuns1,
                                                self.dimensiune_ascuns2, self.dimensiune_iesire)

    def forward(self, x):
        matrice1 = f.relu(np.dot(self.parametri[0], x) + self.parametri[1])
        matrice2 = f.relu(np.dot(self.parametri[2], matrice1) + self.parametri[3])
        matrice3 = f.softmax(np.dot(self.parametri[4], matrice2) + self.parametri[5])
        return matrice3
