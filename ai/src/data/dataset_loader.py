from pathlib import Path
from typing import Dict, List


class DatasetLoader:
    """
    Loads and validates the dataset directory structure.

    Responsibilities:
    - Store dataset paths
    - Verify folder structure
    - Return image file paths

    This class DOES NOT perform preprocessing,
    augmentation, or model training.
    """

    def __init__(self, dataset_root: Path):
        """
        Parameters
        ----------
        dataset_root : Path
            Root directory of the dataset.
        """
        self.dataset_root = Path(dataset_root)

        self.train_dir = self.dataset_root / "train"
        self.validation_dir = self.dataset_root / "validation"
        self.test_dir = self.dataset_root / "test"
    def verify_structure(self) -> bool:
          """
          Check that the required dataset folders exist.

          Returns
          -------
          bool
              True if all required folders exist.
          """

          required_dirs = [
              self.train_dir,
              self.validation_dir,
              self.test_dir
          ]

    for directory in required_dirs:
        if not directory.exists():
            raise FileNotFoundError(
                f"Missing directory: {directory}"
            )

    return True