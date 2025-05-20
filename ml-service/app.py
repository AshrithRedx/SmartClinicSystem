from flask import Flask, request, jsonify
import pandas as pd
import numpy as np
import joblib

app = Flask(__name__)

# Load trained model and encoder
model = joblib.load("ensemble_model.pkl")
encoder = joblib.load("label_encoder.pkl")

# Your feature column names here
symptoms = ["fever", "headache", "nausea", "skin_rash", ...]  # Paste X.columns from notebook
symptom_index = {symptom: idx for idx, symptom in enumerate(symptoms)}

# Disease → Specialist mapping
disease_to_specialist = {
    "Typhoid": ["General Physician"],
    "Hepatitis B": ["Gastroenterologist"],
    "Acne": ["Dermatologist"],
    # Add more...
}

@app.route("/predict", methods=["POST"])
def predict():
    data = request.json
    input_data = [0] * len(symptom_index)

    for symptom, value in data.items():
        if symptom in symptom_index:
            input_data[symptom_index[symptom]] = int(value)

    input_array = np.array(input_data).reshape(1, -1)
    pred_encoded = model.predict(input_array)[0]
    disease = encoder.inverse_transform([pred_encoded])[0]
    specialists = disease_to_specialist.get(disease, ["General Physician"])

    return jsonify({
        "disease": disease,
        "top_specialists": specialists
    })

if __name__ == "__main__":
    app.run(port=5000)
