import pandas as pd
from nltk.corpus import stopwords
import string

data = pd.read_csv('rows.csv', encoding="utf-8")
#data = pd.read_csv('rows.csv', nrows=10000)
f = open('demo.csv', 'w')


header = ['Product', 'Issue', 'Company', 'State', 'Zip Code', 'Complaint ID']

print("Orijinal 'rows.csv' CSV Data: \n")
print(data)

data.pop('Date received')
data.pop('Sub-product')
data.pop('Sub-issue')
data.pop('Consumer complaint narrative')
data.pop('Company public response')
data.pop('Tags')
data.pop('Consumer consent provided?')
data.pop('Submitted via')
data.pop('Date sent to company')
data.pop('Company response to consumer')
data.pop('Timely response?')
data.pop('Consumer disputed?')
data = data.dropna()

stop = stopwords.words('english')

data['Product'] = data['Product'].apply(lambda x: ' '.join([item for item in x.split() if item not in stop]))
data['Issue'] = data['Issue'].apply(lambda x: ' '.join([item for item in x.split() if item not in stop]))
data['Company'] = data['Company'].apply(lambda x: ' '.join([item for item in x.split() if item not in stop]))
# data['State'] = data['State'].apply(lambda x: [item for item in x if item not in stop])
# data['Complaint ID'] = data['Complaint ID'].apply(lambda x: [item for item in x if item not in stop])
# data['Zip Code'] = data['Zip Code'].apply(lambda x: [item for item in x if item not in stop])


def remove_punctuations(text):
    for punctuation in string.punctuation:
        text = text.replace(punctuation, '')
    return text


data['Product'] = data['Product'].apply(remove_punctuations)
data['Issue'] = data['Issue'].apply(remove_punctuations)
data['Company'] = data['Company'].apply(remove_punctuations)
# data['State'] = data['State'].apply(remove_punctuations)
# data['Complaint ID'] = data['Complaint ID'].apply(remove_punctuations)
# data['Zip Code'] = data['Zip Code'].apply(remove_punctuations)

data['Product'] = data['Product'].str.lower()
data['Issue'] = data['Issue'].str.lower()
data['Company'] = data['Company'].str.lower()
# data[''].str.lower()
# data[''].str.lower()
# data[''].str.lower()

data.to_csv("demo.csv", index=False)


print("\nDüzenlenmiş CSV Dosyası':\n")
print(data)
