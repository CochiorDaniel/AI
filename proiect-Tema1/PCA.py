import pandas as pd
import re
from sklearn.decomposition import PCA
from sklearn.preprocessing import StandardScaler


def contine_caracter_non_numeric(sir):
    return bool(re.search(r'[^0-9\.,-]', str(sir)))


file_path = './dataset.csv'
data = pd.read_csv(file_path)
print('Original Dataframe shape :', data.shape)

for column in data.columns:
    if column == "Telephone Number":
        continue
    try:
        numeric_values = pd.to_numeric(data[column], errors='coerce')
        ok = 1
        for el in data[column]:
            if contine_caracter_non_numeric(el):
                ok = 0
                break
        if ok == 0:
            del data[column]

    except ValueError:
        print("Conversion to numeric failed for column")

print('Dataframe shape after removing non-numeric columns:', data.shape)

data_numeric = data.apply(pd.to_numeric, errors='coerce')
data_numeric = data_numeric.dropna()

scaler = StandardScaler()
Z = scaler.fit_transform(data_numeric)

pca = PCA()
principal_components = pca.fit_transform(Z)

explained_variance_ratio = pca.explained_variance_ratio_
print("Explained Variance Ratio:", explained_variance_ratio)

num_components = 2
selected_components = principal_components[:, :num_components]

df_pca = pd.DataFrame(data=selected_components, columns=[f"PC{i + 1}" for i in range(num_components)])

result_df = pd.concat([data_numeric, df_pca], axis=1)

print("PCA Dataframe shape:", result_df.shape)

print("PC1: ", result_df['PC1'])
print("PC2: ", result_df['PC2'])
