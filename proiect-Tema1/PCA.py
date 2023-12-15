import pandas as pd
import matplotlib.pyplot as plt
import re
import numpy as np

def contine_caracter_non_numeric(sir):
    return bool(re.search(r'[^0-9\.,-]', str(sir)))


file_path = './dataset.csv'
data = pd.read_csv(file_path)

for column in data.columns:
    print(column)
    if column == "Telephone Number":
        continue
    try:
        numeric_values = pd.to_numeric(data[column], errors='coerce')
        ok = 1
        for el in data[column]:
            if contine_caracter_non_numeric(el):
                # print(el)
                ok = 0
                break
        if ok == 1:
            # checking shape
            print('Original Dataframe shape :', data.shape)

            # Input features
            X = numeric_values

            # Mean
            X_mean = X.mean()

            # Standard deviation
            X_std = X.std()

            # Standardization
            Z = (X - X_mean) / X_std

            # covariance
            c = Z.cov(numeric_values)

            eigenvalues, eigenvectors = np.linalg.eigh(c)
            # print('Eigen values:\n', eigenvalues)
            # print('Eigen values Shape:', eigenvalues.shape)
            # print('Eigen Vector Shape:', eigenvectors.shape)

        else:
            print("Column does not contain numeric values")

    except ValueError:
        print("Conversion to numeric failed for column")
